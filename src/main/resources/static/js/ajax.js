

        function loadDoc() {
          const xhttp = new XMLHttpRequest();
          xhttp.onload = function () {



          };
          xhttp.open("GET", "/products?brand=FARRERO");
          xhttp.send();
        }
