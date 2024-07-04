let editProductRow = null;

// Function to fetch all products
function getAllProducts() {
    $.ajax({
        url: 'http://localhost:8080/api/product',
        method: 'GET',
        success: function (data) {
            displayProducts(data);
        },
        error: function (error) {
            console.error('Error fetching products:', error);
        }
    });
}

// Function to display products in the table
function displayProducts(products) {
    const productTable = $('#productTable');
    productTable.empty();
    products.forEach(product => {
        const row = `<tr data-id="${product.id}">
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>
                <button class="btn btn-info btn-sm view-btn" data-toggle="modal" data-target="#viewProductModal">View</button>
                <button class="btn btn-primary btn-sm edit-btn" data-toggle="modal" data-target="#editProductModal">Edit</button>
                <button class="btn btn-danger btn-sm delete-btn">Delete</button>
            </td>
        </tr>`;
        productTable.append(row);
    });
}

// Adding a new product
$('#addProductForm').on('submit', function (e) {
    e.preventDefault();
    const product = {
        name: $('#productName').val(),
        description: $('#productDescription').val(),
        country: $('#productCountry').val(),
        price: $('#productPrice').val(),
        quantity: $('#productQuantity').val()
    };

    $.ajax({
        url: 'http://localhost:8080/api/product',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(product),
        success: function () {
            getAllProducts();
            $('#addProductModal').modal('hide');
            $('#addProductForm')[0].reset();
        },
        error: function (error) {
            console.error('Error adding product:', error);
        }
    });
});

// Searching products by name and price range
$('#searchButton').on('click', function () {
    const search = {
        name: $('#search').val().toLowerCase(),
        minPrice: parseFloat($('#minPrice').val()) || null,
        maxPrice: parseFloat($('#maxPrice').val()) || null
    }
    $.ajax({
        url: 'http://localhost:8080/api/product/search',
        method: 'GET',
        contentType: 'application/json',
        data: search,
        success: function (data) {
            displayProducts(data);
        },
        error: function (error) {
            console.error('Error searching products:', error);
        }
    });
});

// Opening modal to view product details
$(document).on('click', '.view-btn', function () {
    editProductRow = $(this).closest('tr');
    const productId = editProductRow.data('id');

    $.ajax({
        url: `http://localhost:8080/api/product/${productId}`,
        method: 'GET',
        success: function (product) {
            $('#viewProductName').text(`Name: ${product.name}`);
            $('#viewProductDescription').text(`Description: ${product.description}`);
            $('#viewProductCountry').text(`Country: ${product.country}`);
            $('#viewProductPrice').text(`Price: ${product.price}`);
            $('#viewProductQuantity').text(`Quantity: ${product.quantity}`);
        },
        error: function (error) {
            console.error('Error fetching product:', error);
        }
    });
});

// Opening modal to edit a product
$(document).on('click', '.edit-btn', function () {
    editProductRow = $(this).closest('tr');
    const productId = editProductRow.data('id');

    $.ajax({
        url: `http://localhost:8080/api/product/${productId}`,
        method: 'GET',
        success: function (product) {
            $('#editProductName').val(product.name);
            $('#editProductDescription').val(product.description);
            $('#editProductCountry').val(product.country);
            $('#editProductPrice').val(product.price);
            $('#editProductQuantity').val(product.quantity);
        },
        error: function (error) {
            console.error('Error fetching product:', error);
        }
    });
});

// Saving edited product
$('#editProductForm').on('submit', function (e) {
    e.preventDefault();
    const productId = editProductRow.data('id');
    const product = {
        id: productId,
        name: $('#editProductName').val(),
        description: $('#editProductDescription').val(),
        country: $('#editProductCountry').val(),
        price: $('#editProductPrice').val(),
        quantity: $('#editProductQuantity').val()
    };

    $.ajax({
        url: `http://localhost:8080/api/product/${productId}`,
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(product),
        success: function () {
            getAllProducts();
            $('#editProductModal').modal('hide');
        },
        error: function (error) {
            console.error('Error updating product:', error);
        }
    });
});

// Deleting a product
$(document).on('click', '.delete-btn', function () {
    const productId = $(this).closest('tr').data('id');

    $.ajax({
        url: `http://localhost:8080/api/product/${productId}`,
        method: 'POST',
        success: function () {
            $(`tr[data-id="${productId}"]`).remove();
        },
        error: function (error) {
            console.error('Error deleting product:', error);
        }
    });
});

// Initial loading of products
getAllProducts();
