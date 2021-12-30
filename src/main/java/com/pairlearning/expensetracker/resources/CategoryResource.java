package com.pairlearning.expensetracker.resources;

import com.pairlearning.expensetracker.domain.Category;
import com.pairlearning.expensetracker.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.pairlearning.expensetracker.helper.Helper.getSuccessBoolMapResponse;
import static com.pairlearning.expensetracker.helper.Helper.getUserId;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/categories")
public class CategoryResource {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories(HttpServletRequest request) {
        List<Category> categories = categoryService.fetchAllCategories(getUserId(request));
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(HttpServletRequest request, @PathVariable("categoryId") Integer categoryId) {
        Category category = categoryService.fetchCategoryById(getUserId(request), categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Category> addCategory(HttpServletRequest request, @RequestBody Map<String, Object> categoryMap) {

        String title = (String) categoryMap.get("title");
        String description = (String) categoryMap.get("description");
        Category category = categoryService.addCategory(getUserId(request), title, description);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Map<String, Boolean>> updateCategory(HttpServletRequest request,
                                                               @PathVariable("categoryId") Integer categoryId,
                                                               @RequestBody Category category) {

        categoryService.updateCategory(getUserId(request), categoryId, category);
        return getSuccessBoolMapResponse();
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Map<String, Boolean>> deleteCategory(HttpServletRequest request,
                                                               @PathVariable("categoryId") Integer categoryId) {

        categoryService.removeCategoryWithAllTransactions(getUserId(request), categoryId);
        return getSuccessBoolMapResponse();
    }



}
