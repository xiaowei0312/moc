KindEditor.plugin('squareBrackets', function(K) {
    var self = this, name = 'squareBrackets';
    self.clickToolbar(name, function() {
    	
    	//定义弹出的页面，join（sp）是js的方法
        var html = ['<div style="margin:20px 20px;">',
                    '  <div class="squareBrackets-inputs">',
                    '    <div class="ke-dialog-row">',
                    '      <input class="ke-input-text" type="text" value="" style="width:250px;" />',
                    '    </div>',
                    '  </div>',
                    '  <div class="ke-dialog-row">',
                    '    <a class="squareBrackets-add-btn" href="javascript:;">+ 添加</a>',
                    '  </div>',
                    '  <div class="ke-dialog-row">',
                    '    <div style="color:#666;">提示：点击 [+添加](或按下回车键)，可为一个填空设置多个答案</div>',
                    '</div>'].join(''),
            
            //定义弹出框
            dialog = self.createDialog({
                name : name,
                width : 450,
                title : '插入填空项',
                body : html,
                yesBtn : {
                    name : self.lang('yes'),
                    click : function(e) {
                        var blankValues = [];
                        K('div.squareBrackets-inputs').scan(function(node){
                            if (K(node).hasClass('ke-input-text')){
                                blankValues.push(K(node).val());
                            }
                        });
                        html = '[' + blankValues.join('|') + ']';
                        self.insertHtml(html).hideDialog().focus();
                    }
                }
            }),
            
        //弹出框的骨干--最外层div
        dialogRoot = dialog.div,
        
        //获取弹出框的输入框
        blankInput = K('.ke-input-text', dialogRoot);
        blankInput[0].focus();
        
        //为编辑器的弹出框中的元素绑定点击事件
        K('.squareBrackets-add-btn', dialogRoot).click(function() {
            var blankInputHtml = [
                    '<div class="ke-dialog-row">',
                    '<input class="ke-input-text" type="text" value="" style="width:250px;" />',
                    '</div>'].join(''),
            //类型的转换？      
            blankInputNode = K(blankInputHtml);
            
            //为编辑器的弹出框中的输入标签集合中添加新的节点
            K('.squareBrackets-inputs', dialogRoot).append(blankInputNode);
            K('.ke-input-text', blankInputNode).get(0).focus();
            dialogRoot.height(dialogRoot.height() + 26);
        });
        
        //为输入值的集合绑定绑定事件
        K('.squareBrackets-inputs', dialogRoot).bind('keydown', function(event) {
        	
        	//若按下的键是回车键
            if (event.keyCode == 13){
                var blankInputHtml = [
                        '<div class="ke-dialog-row">',
                        '<input class="ke-input-text" type="text" value="" style="width:250px;" />',
                        '</div>'].join(''),
                    blankInputNode = K(blankInputHtml);
                K('.squareBrackets-inputs', dialogRoot).append(blankInputNode);
                K('.ke-input-text', blankInputNode).get(0).focus();
                dialogRoot.height(dialogRoot.height() + 26);
            }
        });
    });
});