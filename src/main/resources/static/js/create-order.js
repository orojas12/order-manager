"use strict";

let products = 1;

const productTemplate = document.getElementById("product-template");
const productList = document.getElementById("product-list");

document.getElementById("add-product").addEventListener("click", () => {
    const productElement = productTemplate.cloneNode(true);
    productElement.removeAttribute("hidden");
    productElement.removeAttribute("id");

    productElement.querySelector(".product-name-input")
        .setAttribute("id", `product-name-${products}`);

    productElement.querySelector(".product-name-label")
        .setAttribute("for", `product-name-${products}`);

    productElement.querySelector(".product-desc-input")
        .setAttribute("id", `product-desc-${products}`);

    productElement.querySelector(`.product-desc-label`)
        .setAttribute("for", `product-desc-${products}`);

    productElement.querySelector(".btn-delete-product").addEventListener("click", () => {
        productElement.remove();
    });

    productList.appendChild(productElement);
    products++;
});