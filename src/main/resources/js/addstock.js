/**
 * Created by ChenGeng on 2017/12/10.
 */
function getStockInformation(stockCode) {
    $.ajax({
        type:"post",
        url:'/addStock/getInformation',
        async:true,
        data:{
            'stockCode' : stockCode
        },
        success:function(data){
            $("#stockName").val(data.stockName);
            $("#nowPrice").val(data.nowPrice);
        },
        error: function(){
            alert("ajax请求错误");
        }
    });
}