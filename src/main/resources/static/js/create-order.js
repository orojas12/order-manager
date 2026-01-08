"use strict";

let products = 1;

const productTemplate = document.getElementById("product-template");
const productList = document.getElementById("product-list");

document.getElementById("add-product").addEventListener("click", () => {
    const newProductField = productTemplate.cloneNode(true);
    newProductField.removeAttribute("hidden");
    newProductField.removeAttribute("id");

    newProductField.querySelector(".product-name-input")
        .setAttribute("id", `product-name-${products}`);

    newProductField.querySelector(".product-name-label")
        .setAttribute("for", `product-name-${products}`);

    newProductField.querySelector(".product-desc-input")
        .setAttribute("id", `product-desc-${products}`);

    newProductField.querySelector(`.product-desc-label`)
        .setAttribute("for", `product-desc-${products}`);

    productList.appendChild(newProductField);
    products++;
});