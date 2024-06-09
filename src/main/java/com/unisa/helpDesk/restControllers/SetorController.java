package com.unisa.helpDesk.restControllers;

import com.unisa.helpDesk.dtos.SetorDto;
import com.unisa.helpDesk.services.SetorService;
import com.unisa.helpDesk.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SetorController {
    @Autowired
    private SetorService setorService;

    @PostMapping("/setores")
    public ApiResponse<SetorDto> save(@RequestBody SetorDto dto) {
        return this.setorService.save(dto);
    }

    @GetMapping("/setores")
    public ApiResponse<List<SetorDto>> findAll() {
        return this.setorService.findAll();
    }

    @GetMapping("/setores/{id}")
    public ApiResponse<SetorDto> findById(@PathVariable Long id) {
        return this.setorService.findById(id);
    }

    @PutMapping("/setores/{id}")
    public ApiResponse<SetorDto> updateById(@PathVariable Long id, @RequestBody SetorDto dto) {
        return this.setorService.updateById(id, dto);
    }

    @DeleteMapping("/setores/{id}")
    public ApiResponse<SetorDto> deleteById(@PathVariable Long id) {
        return this.setorService.deleteById(id);
    }

}