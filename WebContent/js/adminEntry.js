$(function(){
    // 全高度
    function set_layout_height_100(){
        let winHeights =[$(window).height(),$(document).height(),$(document.body).height(),$(document.body).outerHeight(true)];
        var maxHeight = winHeights.sort((one,two)=> one < two)[0];
        //maxHeight = $(window).height() ;   
        $('.layout-height-100').height(maxHeight);
        return maxHeight;
    }
    $(window).on('resize',null,null,set_layout_height_100);
    set_layout_height_100();  

    $(document).on('DOMSubtreeModified',null,null,set_layout_height_100);

    /* 设置高度 */
    $('[data-height]').height(function(index, height){
        let dom = $(this);
        return dom.attr('data-height');
    });
    /* 设置宽度 */
    $('[data-width]').width(function(index, width){
        let dom = $(this);
        return dom.attr('data-width');
    });
    /* 设置最小宽度 */ 
    $('[data-min-width]').css('min-width',function(index,width){
        return `${$(this).attr('data-min-width')}px`;
    });
    /* 设置最大宽度 */ 
    $('[data-max-width]').css('max-width',function(index,width){
        return `${$(this).attr('data-max-width')}px`;
    });

    /* 设置最小高度 */ 
    $('[data-min-height]').css('min-height',function(index,width){
        return `${$(this).attr('data-min-height')}px`;
    });
    /* 设置最大高度 */ 
    $('[data-max-height]').css('max-height',function(index,width){
        return `${$(this).attr('data-max-height')}px`;
    });


    $('[data-margin-top]').css('margin-top',function(index,width){
        return `${$(this).attr('data-margin-top')}px`;
    });

    $('[data-margin-bottom]').css('margin-bottom',function(index,width){
        return `${$(this).attr('data-margin-bottom')}px`;
    });

    $(window).on('resize',null,null,function(){
        $('[data-height-all]').css('height',function(index,width){
            let val = set_layout_height_100() - $('.admin-narbar-top').height() - 20;
            return `${val}px`;
        });
    });
    
    $('[data-height-all]').css('height',function(index,width){
        let val = set_layout_height_100() - $('.admin-narbar-top').height() - 20;
        return `${val}px`;
    });

    $('#admin-only-show-505').on('click',null,null,function(){
        var content =$('#admin-only-show-505-content');

        if(content.css('display') == "block"){
            content.css("display","none");
        }else{
            content.css("display","block");
        } 
    });

    $('#admin-only-show-505-content-cancel').on('click',null,null,function(){
        $('#admin-only-show-505-content').css("display","none");
    });


    $('.line-height-all-clenter').css('line-height',function(index,lineheightValue){
        let val_height = $(this).parent('').height()
        let val_min_height = Number.parseInt($(this).parent().css('min-height'));
        
        return Math.max(val_height,val_min_height) + "px";
    });

    
    /* 与加载动画有关的js 脚本 */ 
    $('.loading-admin').css("top",function(index,value){

        let this_height = $(this).find('.modal-body').height();
        let page_height = $(window).height();


        let val = page_height > this_height ? (page_height/2 - this_height - 100) + "px": "0";
        console.info('tag', this_height); 
        console.log(val);
        return val;
    });    

});

/* 与加载动画有关的js 脚本 */
function StartLoading(target = ".loading-admin"){
    appendLoadDom();
    $(target).modal('show');
};

function EndLoading(target = ".loading-admin"){
    $(target).modal('hide');
};

function appendLoadDom(){
    $('body').append($(`
        <div class="modal fade loading-admin" id="loading" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" >
            <div class="modal-dialog " role="document">
                <div class="modal-content">
                    <div class="modal-body" data-min-height="80"  > 
                        <div class="text-center line-height-all-clenter"  >
                            <img src="../Resources/loading/Spinner-1s-200px.svg" data-height="70" > 
                            <span class=" text-primary font-weight-600">拼命加载中...</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>  
    `));
};