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

    el.removeAttribute("hidden");
    el.removeAttribute("id");

    nameInput.setAttribute("id", `product-name-${productCount}`);
    nameLabel.setAttribute("for", `product-name-${productCount}`);
    descInput.setAttribute("id", `product-desc-${productCount}`);
    descLabel.setAttribute("for", `product-desc-${productCount}`);
    priceInput.setAttribute("id", `product-price-${productCount}`);
    priceLabel.setAttribute("for", `product-name-${productCount}`);

    priceInput.addEventListener("change", function () {
        this.value = parseFloat(this.value).toFixed(2);
    });

    deleteBtn.addEventListener("click", () => {
        el.remove();
    });


    productList.appendChild(el);
    productCount++;
});