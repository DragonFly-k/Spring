function addScooter() {
    const token = window.localStorage.getItem("token");
    const model = document.getElementById('model').value;
    const price = document.getElementById('price').value;

    fetch("http://localhost:8080/api/v1/admin/addScooter/"+model+"/"+price, {
        method: 'POST',
        headers: { "Authorization": `Bearer ${token}`},
    }).then(async (response) => {
        if(response.ok) {
            alert('Scooter was added!')
        } else {
            const data = await response.json();
            console.error("Errors", data.errors);
        }
    })
}

function deleteScooter() {
   const token = window.localStorage.getItem("token");
   const model = document.getElementById('model').value;

   fetch("http://localhost:8080/api/v1/admin/deleteScooter/" + model, {
       method: 'DELETE',
       headers: {"Authorization": `Bearer ${token}`}
   }).then(response => {
   console.log(response)
       if(response.ok) {
           alert('Scooter was deleted!')
       } else {
           const data = response.json();
           console.error("Errors", data.errors);
       }
   })
}

//function updateScooter() {
//    const token = window.localStorage.getItem("token");
//    const model = document.getElementById('model');
//    const price = document.getElementById('price');
//
//    fetch("http://localhost:8080/api/v1/admin/updateScooter", {
//        method: 'PUT',
//        headers: {"Authorization": `Bearer ${token}`},
//        body: JSON.stringify({
//            model: model.value,
//            price: parseFloat(price.value),
//        })
//    }).then(async (response) => {
//        if(response.ok) {
//            model.value = '';
//            price.value = '';
//            alert('Scooter was updated!')
//        } else {
//            const data = await response.json();
//            console.error("Errors", data.errors);
//        }
//    })
//}
