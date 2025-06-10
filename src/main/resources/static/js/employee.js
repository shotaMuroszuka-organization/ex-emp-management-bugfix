"use strict";

function fetchAddressByZip() {
    const zipCode = document.getElementById('zipCode').value.replace("-", "").trim();
    if (!zipCode.match(/^\d{7}$/)) {
        return; // 7桁でなければ何もしない
    }

    fetch(`https://zipcoda.net/api?zipcode=${zipCode}`)
        .then(response => response.json())
        .then(data => {
            if (data && data.items && data.items.length > 0) {
                const address = data.items[0].address;
                document.getElementById('address').value = address;
            } else {
                alert("住所が見つかりませんでした");
            }
        })
        .catch(error => {
            console.error("住所取得エラー:", error);
        });
}
