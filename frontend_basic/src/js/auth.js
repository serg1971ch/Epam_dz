if (typeof window !== 'undefined') {
    console.log('You are on the browser')
} else {
    console.log('You are on the server')
}
const list = $(".menu");
const leaf = $('li#3.list');
const menu = document.querySelector('.menu');
// const item = document.getElementsByClassName('list');
const btn = $('input.button');

btn.onclick = function () {
    alert('Спасибо');
    console.log("просто офигеть!")
}
// function ex() {
//     for (let i = 0; i < leaf.length; i++) {
//         console.log(leaf[i]);
//         if (i === 2) {
//             console.log(leaf);
//             return list.innerHTML ='<li id=3 class="list"><a class="out" href="#">Выйти</a></li>';
//         }
//     }
// }
//
// ex()
// btn.onclick = function () {
//     // menu.insertAdjacentHTML('afterend' , '<li id=3 ><a class="out" href="#">Выйти</a></li>');
//     // out.style.background = 'blue';
//     enter.remove();
//     window.location.href = 'index.html';
// }

// btn.addEventListener('click', ex());
btn.addEventListener('click', () =>
    alert('Спасибо'));

// btn.addEventListener('onclick', () => {
//       menu.insertAdjacentText('afterend','<li id=3 ><a class="out" href="auth.html">Выйти</a></li>');
//       enter.remove();
// list.replaceWith(h1);
// h1.classList.add("_name");
// })