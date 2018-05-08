/**
 * Created by ChenGeng on 2017/12/9.
 */
$("#tingPaiStockList").datagrid({
    url:'list',
    pagination:false,
    columns:[[
        {field:'stockCode',title:'股票代码'},
        {field:'stockName',title:'股票名称'},
        {field:'stopDateTime',title:'停牌时间'},
        {field:'continueDateTime',title:'复牌时间'},
        {field:'type',title:'停牌类型'},
        {field:'reason',title:'停牌原因'},
        {field:'marketPlate',title:'所在板块'}
    ]]
})