
/**
 * http://akquinet.github.io/jquery-toastmessage-plugin/demo/demo.html
 * @returns {undefined}
 */

/*
function showSuccessToast() {
    $().toastmessage('showSuccessToast', "Success Dialog which is fading away ...");
}
*/
function showSuccessToast(texto) {
    $().toastmessage('showSuccessToast', ""+texto);
}

function showStickySuccessToast() {
    $().toastmessage('showToast', {
        text: 'Projeto cadastrado com sucesso!',
        sticky: true,
        //position: 'top-right',
        position: 'top-center',
        type: 'success',
        //type: 'error',
        closeText: '',
        close: function() {
            console.log("toast is closed ...");
        }
    });

}
function showNoticeToast() {
    $().toastmessage('showNoticeToast', "Notice  Dialog which is fading away ...");
}
function showStickyNoticeToast() {
    $().toastmessage('showToast', {
        text: 'Notice Dialog which is sticky',
        sticky: true,
        position: 'top-right',
        type: 'notice',
        closeText: '',
        close: function() {
            console.log("toast is closed ...");
        }
    });
}
function showWarningToast() {
    $().toastmessage('showWarningToast', "Warning Dialog which is fading away ...");
}
function showStickyWarningToast() {
    $().toastmessage('showToast', {
        text: 'Warning Dialog which is sticky',
        sticky: true,
        position: 'top-right',
        type: 'warning',
        closeText: '',
        close: function() {
            console.log("toast is closed ...");
        }
    });
}
function showErrorToast() {
    $().toastmessage('showErrorToast', "Error Dialog which is fading away ...");
}

function showStickyErrorToast() {
    $().toastmessage('showToast', {
        text: 'Error Dialog which is sticky',
        sticky: true,
        position: 'top-right',
        type: 'error',
        closeText: '',
        close: function() {
            console.log("toast is closed ...");
        }
    });
}

function showStickyErrorToast(texto) {
    $().toastmessage('showToast', {
        //text: 'Error Dialog which is sticky',
        text: texto,
        sticky: true,
        position: 'top-right',
        type: 'error',
        closeText: '',
        close: function() {
            console.log("toast is closed ...");
        }
    });
}