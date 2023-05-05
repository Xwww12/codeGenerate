package com.xw.code_generate.controller;

import com.xw.code_generate.model.ResBean;
import com.xw.code_generate.model.TableClass;
import com.xw.code_generate.service.CodeGenerateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CodeGenerateController {
    @Resource
    CodeGenerateService generateService;

    @PostMapping("/codeGenerate")
    public ResBean generate(@RequestBody List<TableClass> tableClassList, HttpServletRequest request) {
        return generateService.generateCode(tableClassList, request);
    }
}
