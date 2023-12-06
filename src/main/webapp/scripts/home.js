// função do calendario
$(function () {
  $('input[name="daterange"]').daterangepicker({
    opens: 'left'
  }, function (start, end, label) {
    console.log("A new date selection was made: " + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
  });
});

// carousel
const carousel = document.getElementById("carousel");
let isDragging = false;
let startX;
let scrollLeft;

carousel.addEventListener("mousedown", (e) => {
  isDragging = true;
  startX = e.pageX - carousel.offsetLeft;
  scrollLeft = carousel.scrollLeft;
});

carousel.addEventListener("mouseup", () => {
  isDragging = false;
});

carousel.addEventListener("mousemove", (e) => {
  if (!isDragging) return;
  const x = e.pageX - carousel.offsetLeft;
  const walk = (x - startX) * 2;
  carousel.scrollLeft = scrollLeft - walk;
});

carousel.addEventListener("mouseleave", () => {
  isDragging = false;
});

function nextSlide() {
  carousel.scrollLeft += 200;
}

function prevSlide() {
  carousel.scrollLeft -= 200;
}

let hotel = [
  "Copacabana Palace",
  "Ipanema Beach Resort",
  "Amazon Rainforest Lodge",
  "Pantanal Oasis Hotel",
  "São Paulo Skyscraper Hotel",
  "Fernando de Noronha Paradise Resort",
  "Salvador Sunrise Inn",
  "Brasília City Center Hotel",
  "Foz do Iguaçu Waterfall Hotel",
  "Ouro Preto Heritage Mansion"
];

let sortedHotel = hotel.sort();
//console.log(sortedHotel);

let input = document.getElementById("destino");

input.addEventListener("keyup", (e) => {

  removeElements();
  for (let i of sortedHotel) {
    if (i.toLowerCase().startsWith(input.value.toLowerCase()) && input.value !== "") {
      let listItem = document.createElement("li");
      listItem.classList.add("list-items");
      listItem.style.cursor = "pointer";
      listItem.setAttribute("onclick", "displayHotel('" + i + "')");

      let word = "<b>" + i.substr(0, input.value.length) + "</b>";
      word += i.substr(input.value.length);

      listItem.innerHTML = word;
      document.querySelector(".label").appendChild(listItem)
    }
  }
});

function removeElements() {
  let items = document.querySelectorAll(".list-items");
  items.forEach((item) => {
    item.remove();
  })
}

function displayHotel(value) {
  input.value = value;
  removeElements();
}


document.getElementById('submit').addEventListener('click', function (event) {
  event.preventDefault();

  window.location.href = 'cadastro.html'; // Substitua '#cadastro-section' pelo ID correto da seção de cadastro
});


