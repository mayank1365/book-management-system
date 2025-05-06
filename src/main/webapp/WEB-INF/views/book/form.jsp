<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>${book.id == null ? 'Add New Book' : 'Edit Book'}</h2>

<form action="${book.id == null ? '/books' : '/books/update/' + book.id}" method="post">
    <div class="form-group">
        <label for="title">Title:</label>
        <input type="text" class="form-control" id="title" name="title" value="${book.title}" required>
    </div>
    
    <div class="form-group">
        <label for="isbn">ISBN:</label>
        <input type="text" class="form-control" id="isbn" name="isbn" value="${book.isbn}" required>
    </div>
    
    <div class="form-group">
        <label for="description">Description:</label>
        <textarea class="form-control" id="description" name="description" rows="3">${book.description}</textarea>
    </div>
    
    <div class="form-group">
        <label for="price">Price:</label>
        <input type="number" step="0.01" class="form-control" id="price" name="price" value="${book.price}" required>
    </div>
    
    <div class="form-group">
        <label for="publicationDate">Publication Date:</label>
        <input type="date" class="form-control" id="publicationDate" name="publicationDate" value="${book.publicationDate}">
    </div>
    
    <div class="form-group">
        <label for="author">Author:</label>
        <select class="form-control" id="author" name="author.id" required>
            <c:forEach items="${authors}" var="author">
                <option value="${author.id}" ${book.author.id == author.id ? 'selected' : ''}>
                    ${author.name}
                </option>
            </c:forEach>
        </select>
    </div>
    
    <button type="submit" class="btn btn-primary">Save</button>
    <a href="/books" class="btn btn-secondary">Cancel</a>
</form> 