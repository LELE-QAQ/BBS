var clickX;  
var clickY;  
//获取事件  
function getEvent() {  
    if (document.all) {  
        return window.event;// 如果是ie  
    }  
    func = getEvent.caller;  
    while (func != null) {  
        var arg0 = func.arguments[0];  
        if (arg0) {  
            if ((arg0.constructor == Event || arg0.constructor == MouseEvent)  
                    || (typeof(arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {  
                return arg0;  
            }  
        }  
        func = func.caller;  
    }  
    return null;  
}  
//在此函数中给clickX和clickY赋值  
function getClickPosition(){  
    var evt=getEvent();  
    if(null!=evt){  
        var el=evt.target||evt.srcElement;  
        var top=0;  
        var left=0;  
        while(el){//递归求元素所在页面的位置  
            top+=el.offsetTop;//el.offsetTop的值是el元素的上起始位置相对于父元素的上起始位置的差值，所以需要递归相加  
            left+=el.offsetLeft;  
            el=el.offsetParent;  
        }  
        clickX=left;  
        clickY=top;  
    }else{  
        }  
} 