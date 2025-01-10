package com.nttemoi.warehouse.controlllers;

import com.nttemoi.warehouse.dtos.ProductDTO;
import com.nttemoi.warehouse.dtos.ProductbomDTO;
import com.nttemoi.warehouse.dtos.SupplierDTO;
import com.nttemoi.warehouse.services.impl.ProductServiceImpl;
import com.nttemoi.warehouse.services.impl.ProductbomServiceImpl;
import com.nttemoi.warehouse.services.impl.SupplierServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductServiceImpl productService;
    private final ProductbomServiceImpl productbomService;
    private final SupplierServiceImpl supplierService;

    @Autowired
    public ProductController(SupplierServiceImpl supplierService, ProductServiceImpl productService, ProductbomServiceImpl productbomService) {
        this.supplierService = supplierService;
        this.productService = productService;
        this.productbomService = productbomService;
    }

    @GetMapping
    public String getAll(Model model,
                         @RequestParam(required = false) String keyword,
                         @RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "10") int size,
                         @RequestParam(required = false) String order,
                         @RequestParam(required = false) String orderBy) {
        Page<ProductDTO> pageTuts;
        try {
            if (keyword == null) {
                if (order != null) {
                    pageTuts = productService.findAllAndSortDTO(page - 1, size, order, orderBy);
                } else {
                    pageTuts = productService.findAllDTO(page - 1, size);
                }
            } else {
                if (order != null) {
                    pageTuts = productService.findByKeywordAndSortDTO(keyword, page - 1, size, order, orderBy);
                } else {
                    pageTuts = productService.findByKeyword(keyword, page - 1, size);
                }
                model.addAttribute("keyword", keyword);
            }
            if (order != null) {
                model.addAttribute("order", order);
                model.addAttribute("orderBy", orderBy);
            }

            model.addAttribute("products", pageTuts.getContent());
            model.addAttribute("currentPage", pageTuts.getNumber() + 1);
            model.addAttribute("totalItems", pageTuts.getTotalElements());
            model.addAttribute("totalPages", pageTuts.getTotalPages());
            model.addAttribute("pageSize", size);
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching products: " + e.getMessage());
        }
        return "show-product";
    }

    @GetMapping("/detail/{id}")
    public String getAllBom(Model model, @PathVariable Long id,
                            @RequestParam(required = false) String keyword,
                            @RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int size,
                            @RequestParam(required = false) String order,
                            @RequestParam(required = false) String orderBy) {
        Page<ProductbomDTO> pageTuts;
        if (keyword == null) {
            if (order != null) {
                pageTuts = productbomService.findAllByProductIdAndSortDTO(id, page - 1, size, order, orderBy);
            } else {
                pageTuts = productbomService.findAllByProductIdDTO(id, page - 1, size);
            }
        } else {
            if (order != null) {
                pageTuts = productbomService.findAllByProductIdAndSortDTO(id, page - 1, size, order, orderBy);
            } else {
                pageTuts = productbomService.findByKeywordAndProductIdDTO(keyword, id, page - 1, size);
            }
            model.addAttribute("keyword", keyword);
        }
        if (order != null) {
            model.addAttribute("order", order);
            model.addAttribute("orderBy", orderBy);
        }
        model.addAttribute("productboms", pageTuts.getContent());
        model.addAttribute("currentPage", pageTuts.getNumber() + 1);
        model.addAttribute("totalItems", pageTuts.getTotalElements());
        model.addAttribute("totalPages", pageTuts.getTotalPages());
        model.addAttribute("pageSize", size);
        return "show-productbom";
    }
    @GetMapping("/new")
    public String addProduct(Model model) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPublished(true);
        List<SupplierDTO> suppliers = supplierService.findAllDTO();
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("product", productDTO);
        return "add-product";
    }

    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") ProductDTO productDTO,
                              RedirectAttributes redirectAttributes) {
        try {
            if (productDTO.getName() == null || productDTO.getName().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Product name cannot be empty!");
                return "redirect:/product/new";
            }
            if(productDTO.getProductbomlist()!=null){
            for (ProductbomDTO bom : productDTO.getProductbomlist()) {
                if (bom.getName() == null || bom.getName().isEmpty()) {
                    redirectAttributes.addFlashAttribute("error", "Product BOM name cannot be empty!");
                    return "redirect:/product/new";
                }
                if (bom.getQuantity() <= 0) {
                    redirectAttributes.addFlashAttribute("error", "Product BOM quantity must be greater than 0!");
                    return "redirect:/product/new";
                }
                if (bom.getUnit() == null || bom.getUnit().isEmpty()) {
                    redirectAttributes.addFlashAttribute("error", "Product BOM unit cannot be empty!");
                    return "redirect:/product/new";
                }
            }
            }
            productService.saveDTO(productDTO);
            redirectAttributes.addFlashAttribute("message", "The Product has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to save the product: " + e.getMessage());
            return "redirect:/product/new";
        }
        return "redirect:/product";
    }


    @GetMapping("/{id}")
    public String editProductAndProductbom(@PathVariable("id") Long id,
                                           Model model) {
        ProductDTO existingProduct = productService.findByIdDTO(id);
        List<ProductbomDTO> productbomList = existingProduct.getProductbomlist();
        if (productbomList == null) {
            productbomList = new ArrayList<>();
        }
        List<SupplierDTO> suppliers = supplierService.findAllDTO();
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("product", existingProduct);
        model.addAttribute("productbomList", productbomList);
        return "add-product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id,
                                RedirectAttributes redirectAttributes) {
        try {
            productService.deleteByIdDTO(id);
            redirectAttributes.addFlashAttribute("message", "The Product has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/product";
    }

    @GetMapping("/submit")
    public String submitProduct( @ModelAttribute ProductDTO productDTO,
                                 RedirectAttributes redirectAttributes) {
        try {
            productService.saveDTO(productDTO);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to submit the product: " + e.getMessage());
        }
        return "redirect:/product";
    }

    @GetMapping("/{id}/published/{status}")
    public String updateProductPublishedStatus(@PathVariable("id") Long id,
                                               @PathVariable("status") boolean published,
                                               RedirectAttributes redirectAttributes) {
        try {
            productService.updatePublishedStatus(id, published);
            String statusMessage = published ? "published" : "disabled";
            String message = "The Product has been " + statusMessage;
            redirectAttributes.addFlashAttribute("message", message);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/product";
    }

}
