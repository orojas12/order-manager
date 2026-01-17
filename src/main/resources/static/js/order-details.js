"use strict";

const element = document.querySelector(".toast");

if (element) {
    const toast = bootstrap.Toast.getOrCreateInstance(element);
    toast.show();
}