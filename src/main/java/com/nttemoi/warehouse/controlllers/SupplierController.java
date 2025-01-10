package com.nttemoi.warehouse.controlllers;

import com.nttemoi.warehouse.dtos.SupplierDTO;
import com.nttemoi.warehouse.services.impl.SupplierServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping ("/supplier")
public class SupplierController {
    private final SupplierServiceImpl supplierService;

    public SupplierController(SupplierServiceImpl supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public String getAll(Model model,
                         @RequestParam(required = false) String keyword,
                         @RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "10") int size,
                         @RequestParam(required = false) String order,
                         @RequestParam(required = false) String orderBy) {
        Page<SupplierDTO> pageTuts;

        if (keyword == null) {
            if (order != null) {
                pageTuts = supplierService.findAllAndSortDTO(page - 1, size, order, orderBy);
            } else {
                pageTuts = supplierService.findAllDTO(page - 1, size);
            }
        } else {
            if (order != null) {
                pageTuts = supplierService.findByKeywordAndSortDTO(keyword, page - 1, size, order, orderBy);
            } else {
                pageTuts = supplierService.findByKeywordDTO(keyword, page - 1, size);
            }
            model.addAttribute("keyword", keyword);
        }
        if (order != null) {
            model.addAttribute("order", order);
            model.addAttribute("orderBy", orderBy);
        }

        model.addAttribute("suppliers", pageTuts.getContent());
        model.addAttribute("currentPage", pageTuts.getNumber() + 1);
        model.addAttribute("totalItems", pageTuts.getTotalElements());
        model.addAttribute("totalPages", pageTuts.getTotalPages());
        model.addAttribute("pageSize", size);

        return "show-supplier";
    }

    @GetMapping("/new")
    public String addSupplier(Model model) {
        SupplierDTO supplier = new SupplierDTO();
        supplier.setStatus(true);

        model.addAttribute("suppliers", supplier);

        return "add-supplier";
    }

    @PostMapping("/save")
    public String saveSupplier(SupplierDTO supplier,
                               RedirectAttributes redirectAttributes) {
        supplierService.saveDTO(supplier);

        redirectAttributes.addFlashAttribute("message", "The Supplier has been saved successfully!");
        return "redirect:/supplier";
    }

    @GetMapping("/{id}")
    public String editSupplier(@PathVariable("id") Long id,
                               Model model) {
        SupplierDTO supplier = supplierService.findByIdDTO(id);

        model.addAttribute("suppliers", supplier);
        return "add-supplier";
    }

    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable("id") Long id,
                                 RedirectAttributes redirectAttributes) {
        try {
            supplierService.deleteByIdDTO(id);

            redirectAttributes.addFlashAttribute("message", "The Supplier has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/supplier";
    }

    @GetMapping("/{id}/status/{status}")
    public String updateSupplierPublishedStatus(@PathVariable("id") Long id,
                                                @PathVariable("status") boolean status,
                                                RedirectAttributes redirectAttributes) {
        try {
            supplierService.updatePublishedStatus(id, status);

            String status1 = status ? "status" : "disabled";
            String message = "The Supplier has been " + status1;

            redirectAttributes.addFlashAttribute("message", message);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/supplier";
    }
}