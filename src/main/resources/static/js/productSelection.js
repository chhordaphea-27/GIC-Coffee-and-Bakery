var order = {
    id: 0,
    user: {},
    dateCreate: 0, 
    totalPrice: 0,
    table: {},
    totalPrice: 0

}
var orderItem = {}



function displayPopupOptionPopup(event, data) {
    document.getElementById("order-wrapper").style.display = "block";
    
    const productStr = data.getAttribute("data-product")
    const productSizeList = JSON.parse(productStr)

    document.getElementById("titleDailog").innerHTML = productSizeList.product.name

    console.log("product: ", productSizeList)

    const sizeSelection = document.getElementById("sizeSelection")
    sizeSelection.innerHTML = null
    productSizeList.sizes.forEach(size => {
        var opt = document.createElement('option');
            opt.value = size.id;
            opt.innerHTML = size.sizeName;
            sizeSelection.appendChild(opt);
    });


    document.getElementById("product_id").value = productSizeList.product.id;

    console.log("ProductID: ", document.getElementById("product_id").value)


}



function closePopupOptionPopup() {
    document.getElementById("order-wrapper").style.display = "none";
    console.log("addCream", document.getElementById("addCream").value)

}

function displayPayment(event, data) {
    document.getElementById("payamount-wrapper").style.display = "block";

    var orderStr = data.getAttribute("data-orderAndItemList");
    
    var order = JSON.parse(orderStr);
    console.log("Order: ", order);

    document.getElementById("total").innerHTML  =  order.totalPrice;
    document.getElementById("total").value  =  order.totalPrice;
    
}

function closePayment() {
    document.getElementById("payamount-wrapper").style.display = "none";
}

