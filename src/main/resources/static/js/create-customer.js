"use strict";

const addressInputIds = ["street", "city", "state", "postal-code", "country"];

const addressInputs = addressInputIds
    .map(id => document.getElementById(id))
    .filter(element => element !== null);


const includeAddressInput = document.getElementById("include-address");

if (includeAddressInput) {

    includeAddressInput.addEventListener("change", (e) => {
        addressInputs.forEach(input => {
            if (e.currentTarget.checked) {
                input.disabled = false;
            } else {
                input.disabled = true;
                input.value = "";
            }
        })
    })
}

