"use strict";

let productCount = 0;

const productTemplate = document.getElementById("product-card-template");
const productList = document.getElementById("product-list");

document.getElementById("add-product").addEventListener("click", () => {
    const el = productTemplate.cloneNode(true);
    const nameInput = el.querySelector(".product-name-input");
    const nameLabel = el.querySelector(".product-name-label");
    const descInput = el.querySelector(".product-desc-input");
    const descLabel = el.querySelector(".product-desc-label");
    const deleteBtn = el.querySelector(".btn-delete-product");
    const priceInput = el.querySelector(".product-price-input");
    const priceLabel = el.querySelector(".product-price-label");
    const quantityInput = el.querySelector(".product-quantity-input");
    const quantityLabel = el.querySelector(".product-quantity-label");

    el.removeAttribute("hidden");
    el.removeAttribute("id");

    nameInput.setAttribute("name", `products[${productCount}].name`)
    nameInput.setAttribute("id", `product-name-${productCount}`);
    nameLabel.setAttribute("for", `product-name-${productCount}`);

    descInput.setAttribute("name", `products[${productCount}].desc`)
    descInput.setAttribute("id", `product-desc-${productCount}`);
    descLabel.setAttribute("for", `product-desc-${productCount}`);

    priceInput.setAttribute("name", `products[${productCount}].unitPrice`)
    priceInput.setAttribute("id", `product-price-${productCount}`);
    priceLabel.setAttribute("for", `product-price-${productCount}`);

    quantityInput.setAttribute("name", `products[${productCount}].quantity`);
    quantityInput.setAttribute("id", `product-quantity-${productCount}`);
    quantityLabel.setAttribute("for", `product-quantity-${productCount}`)


    deleteBtn.addEventListener("click", () => {
        el.remove();
    });


    productList.appendChild(el);
    productCount++;
});