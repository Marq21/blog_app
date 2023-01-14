const modal = document.getElementById('myModal');

// Get the image and insert it inside the modal - use its "alt" text as a caption
const img = document.getElementById('full-txt-mainImg');
const modalImg = document.getElementById("img01");
const captionText = document.getElementById("caption");
img.onclick = function(){
    modal.style.display = "block";
    modalImg.src = this.src;
    captionText.innerHTML = this.alt;
}

const span = document.getElementsByClassName("close")[0];

span.onclick = function() {
    modal.style.display = "none";
}
