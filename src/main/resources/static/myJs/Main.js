$(document).ready(function () {
    $('#nav-icon').click(function () {
        $(this).toggleClass('open');
        $('#sidebar').toggleClass('open');
        $('#panelTool').toggleClass('hidden');
    });
});

//-----------ДЛЯ ПЕРЕТАСКИВАНИЯ СКРОЛБАРА-------------------
// $(document).ready(function () {
//     let pointerFrom = 0;
//     let elementFrom = 0;
//
//     const scrollable = document.querySelector('.wrapper');
//
//     const onDrag = (event) => {
//         if (event.pointerType == 'mouse') {
//             scrollable.scrollLeft = elementFrom - event.clientX + pointerFrom;
//         }
//     };
//
//     scrollable.addEventListener('pointerdown', (event) => {
//         if (event.pointerType == 'mouse') {
//             pointerDown = true;
//             // Set the position where the mouse is starting to drag from.
//             pointerFrom = event.clientX;
//             // Set the position of the element is scrolled from.
//             elementFrom = scrollable.scrollLeft;
//             // React on pointer move.
//             document.addEventListener('pointermove', onDrag);
//         }
//     });
//
// // Stop reacting on pointer move when pointer is no longer clicked.
//     document.addEventListener('pointerup', (event) => {
//         // Ensure we only do this for pointers that don't have native
//         // drag-scrolling behavior.
//         if (event.pointerType == 'mouse') {
//             document.removeEventListener('pointermove', onDrag);
//         }
//     });
// });

//---------------------------------------------------------------------------
$(document).ready(function () {
    console.log(window.innerWidth)
    const buttonRight = document.getElementById('slideRight');
    const buttonLeft = document.getElementById('slideLeft');
    var container = document.getElementById('scroller');

    buttonRight.onclick = function () {
        sideScroll(container,'right',15,350,20);
    };
    buttonLeft.onclick = function () {
        sideScroll(container,'left',15,350,20);
    };
});

function sideScroll(element,direction,speed,distance,step){
    var scrollAmount = 0;
    var slideTimer = setInterval(function(){
        if(direction == 'left'){
            element.scrollLeft -= step;
        } else {
            element.scrollLeft += step;
        }
        scrollAmount += step;
        if(scrollAmount >= distance){
            window.clearInterval(slideTimer);
        }
    }, speed);
}
