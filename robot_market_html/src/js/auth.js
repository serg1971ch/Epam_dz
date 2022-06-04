
// const enter = document.getElementById("1");
// const out = document.getElementById('3');
// const menu = document.querySelector('.menu');
const item = document.getElementsByClassName('list');
const btn = document.getElementsByClassName('button');
const delItem = function () {
    for (let i = 0; i < item.length; i++) {
        console.log(item[i]);
        if (i === 4) {
            item[i].remove();
        }
    }
}
delItem();
// btn.onclick = function () {
//     // menu.insertAdjacentHTML('afterend' , '<li id=3 ><a class="out" href="#">Выйти</a></li>');
//     // out.style.background = 'blue';
//     enter.remove();
//     window.location.href = 'index.html';
// }

// btn.addEventListener('click', delItem);

// btn.addEventListener('onclick', () => {
//       menu.insertAdjacentText('afterend','<li id=3 ><a class="out" href="auth.html">Выйти</a></li>');
//       enter.remove();
// list.replaceWith(h1);
// h1.classList.add("_name");
// })