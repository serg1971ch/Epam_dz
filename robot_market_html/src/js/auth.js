const btn = document.getElementById('2');
const enter = document.getElementById("1");
const out = document.getElementById('3');
const menu = document.querySelector('.menu');
const item = document.getElementsByTagName('ul.li');


btn.onclick = function () {
    // menu.insertAdjacentHTML('afterend' , '<li id=3 ><a class="out" href="#">Выйти</a></li>');
    // out.style.background = 'blue';
    enter.remove();
    window.location.href = 'index.html';
}

// btn.addEventListener('click', logIn);

// btn.addEventListener('onclick', () => {
//       menu.insertAdjacentText('afterend','<li id=3 ><a class="out" href="auth.html">Выйти</a></li>');
//       enter.remove();
    // list.replaceWith(h1);
    // h1.classList.add("_name");
// })