//Open and close Form
$(document).ready(function() {
    let span = $(".close")[0];
  
    // When the user clicks the button, open the modal 
    $('body').on('click', '.addNew', function() {
        document.getElementById("myModal").style.display = "block";
    })

    $('body').on('click', '.editBtn', function() {
        document.getElementById("myModal").style.display = "block";
    })
  
    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        document.getElementById("myModal").style.display = "none";
    }
})

// Fetch stored Categories
$(document).ready(function() {
    let addButton = $('<td colspan="6"><button class="addNew">Add Category</a></button></td>');
            
    $('#categories tfoot').append(addButton);
    $.getJSON('fetch_categories.php', function(data) {
        // Iterate over each row of data
        $.each(data, function(index, rowData) {
            // Create a new row for each item
            let row = $('<tr></tr>');
            
            // Add cells for each field
            row.append($('<td></td>').text(rowData.id)); // ID
            row.append($('<td></td>').text(rowData.date_created)); // Date Created
            row.append($('<td></td>').text(rowData.name)); // Name
            row.append($('<td></td>').text(rowData.description)); // Description
            row.append($('<td></td>').append($('<span></span>').addClass(rowData.status === 'Active'? 'statusActive' : 'statusInactive').text(rowData.status))); // Status
            row.append($('<td></td>').append('<button class="editCatBtn editBtn" action="update_category.php" data-id="' + rowData.id + '"><img src="img/edit.png" alt="Edit"></button>').append('<button class="deleteCatBtn" data-id="' + rowData.id + '"><img src="img/delete.png" alt="Delete"></button>').addClass('actionBtn'));
            
            // Append the row to the table body
            $('#categories tbody').append(row);
        });
    });
});

// Fetch stored Products
$(document).ready(function() {
    let addButton = $('<td colspan="6"><button class="addNew">Add Product</a></button></td>');
            
    $('#products tfoot').append(addButton);
    $.getJSON('fetch_products.php', function(data) {
        // Iterate over each row of data
        $.each(data, function(index, rowData) {
            // Create a new row for each item
            let row = $('<tr></tr>');
            
            // Add cells for each field
            row.append($('<td></td>').text(rowData.id)); // ID
            row.append($('<td></td>').text(rowData.date_created)); // Date Created
            row.append($('<td></td>').text(rowData.category)); // Category
            row.append($('<td></td>').text(rowData.name)); // Name
            row.append($('<td></td>').text(rowData.price)); // Price
            row.append($('<td></td>').append($('<span></span>').addClass(rowData.status === 'Active'? 'statusActive' : 'statusInactive').text(rowData.status))); // Status
            row.append($('<td></td>').append('<button class="editProdBtn editBtn" action="update_product.php" data-id="' + rowData.id + '"><img src="img/edit.png" alt="Edit"></button>').append('<button class="deleteProdBtn" data-id="' + rowData.id + '"><img src="img/delete.png" alt="Delete"></button>').addClass('actionBtn'));
                        
            // Append the row to the table body
            $('#products tbody').append(row);
        }); 
    });
});

//Put existing categories into the Add Product category dropdown
$(document).ready(function() {
    $.getJSON('fetch_categories.php', function(data) {
        let activeCategories = data.filter(category => category.status === 'Active').map(category => category.name);

        // Populate the dropdown with the fetched categories
        $("#productCategory").empty(); // Clear existing options
        activeCategories.forEach(function(category) {
            $("#productCategory").append(`<option value="${category}">${category}</option>`);
        });
    });
});

//--Update/Edit Details--
// Fill the modal with category details
function fillModalWithCategoryDetails(id) {
    $.getJSON('fetch_categories.php', function(data) {
        const category = data.find(c => c.id == id);
        if (category) {
            $('#name').val(category.name);
            $('#description').val(category.description);
            $('#status').val(category.status);
            $('#categoryId').val(category.id); // Set the category id
        }
    });
}

// Fill the modal with product details
function fillModalWithProductDetails(id) {
    $.getJSON('fetch_products.php', function(data) {
        const product = data.find(p => p.id == id);
        if (product) {
            $('#productCategory').val(product.category);
            $('#name').val(product.name);
            $('#price').val(product.price);
            $('#status').val(product.status);
            $('#productId').val(product.id);
        }
    });
}

// Event listener for edit buttons
$('body').on('click', '.editCatBtn', function() {
    const id = $(this).data('id');
    fillModalWithCategoryDetails(id);
    document.getElementById("myModal").style.display = "block";
});

$('body').on('click', '.editProdBtn', function() {
    const id = $(this).data('id');
    fillModalWithProductDetails(id);
    document.getElementById("myModal").style.display = "block";
});

//Add New Category
$('body').on('submit', '#categoryForm', function(e) {
    e.preventDefault();

    let formData = $(this).serialize();
    let actionUrl = $('#categoryId').val()? 'update_category.php' : 'add_category.php'; 

    $.ajax({
        url: actionUrl,
        type: 'POST',
        data: formData,
        success: function(response) {
            location.reload();
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('Error updating category:' + textStatus);
        }
    });
});

//Add New Product
$('body').on('submit', '#productForm', function(e) {
    e.preventDefault();

    let formData = $(this).serialize();
    let actionUrl = $('#productId').val()? 'update_product.php' : 'add_product.php'; 

    $.ajax({
        url: actionUrl,
        type: 'POST',
        data: formData,
        success: function(response) {
            location.reload();
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('Error updating product:' + textStatus);
        }
    });
});

//Delete Category
  $('body').on('click', '.deleteCatBtn', function() {
    let categoryId = $(this).data('id'); // Get the ID from the data-id attribute
    $.ajax({
        url: 'delete_category.php',
        type: 'POST',
        data: {id: categoryId},
        success: function(response) {
            location.reload(); // Reload the page to reflect the changes
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error(textStatus, errorThrown);
        }
    });
});

//Delete Product
$('body').on('click', '.deleteProdBtn', function() {
    let productId = $(this).data('id'); // Get the ID from the data-id attribute
    $.ajax({
        url: 'delete_product.php',
        type: 'POST',
        data: {id: productId},
        success: function(response) {
            location.reload(); // Reload the page to reflect the changes
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error(textStatus, errorThrown);
        }
    });
});

