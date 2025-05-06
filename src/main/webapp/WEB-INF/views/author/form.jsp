<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>${author.id == null ? 'Add New Author' : 'Edit Author'}</h2>

<form action="${author.id == null ? '/authors' : '/authors/update/' + author.id}" method="post">
    <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" class="form-control" id="name" name="name" value="${author.name}" required>
    </div>
    
    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" class="form-control" id="email" name="email" value="${author.email}" required>
    </div>
    
    <div class="form-group">
        <label for="biography">Biography:</label>
        <textarea class="form-control" id="biography" name="biography" rows="3">${author.biography}</textarea>
    </div>
    
    <button type="submit" class="btn btn-primary">Save</button>
    <a href="/authors" class="btn btn-secondary">Cancel</a>
</form> 