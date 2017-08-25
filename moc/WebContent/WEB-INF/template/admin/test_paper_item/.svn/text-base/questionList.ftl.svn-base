   <table class="optiontable" cellpadding="0" cellspacing="0">
        	<tr>
            <th width="10%">编号</th>
            <th width="20%">类型</th>
            <th width="60%">题干</th>
            <th width="10%">操作</th>
            </tr>
            [#list questionList as question]
           	 <tr id="question_${question.id}" class="tr_of_question" style="background:#ffffff">
	            <td class="td_of_question">
	            	${question.id}
	            	[#if question.questionType = 'material' ]
		            	<div id="parent_${question.id}">
           				 	[#list question.children as childrens]
		            			<div class="question_children">
		            					<input type="hidden"   name="id_type" value="${childrens.id}-${childrens.questionType}-${childrens.score}-${childrens.missScore}">
			            				<div class="children_stem">
											${childrens.stemToHtml}
			            				</div>
							            <div class="children_popbox">
						                	<b>题干:</b
						                    <p> ${childrens.stemToHtml}</p>
						                    [#if childrens.questionType = 'single_choice' || childrens.questionType = 'choice' || childrens.questionType = 'uncertain_singlechoice' || childrens.questionType = 'uncertain_choice' ]
												<b>选项：</b>
						                    	 [#list childrens.toMetas as meta]
						                   		 	 <p>${meta}</p>
						                    	 [/#list]
						                    	 <b>答案:</b>
						                   		 <p>${childrens.toAnswer}</p>
											[/#if]
						                    [#if childrens.questionType = 'determine']
						                    	 <b>答案:</b>
						                   		 <p>${childrens.toAnswer}</p>
											[/#if]
						                    [#if childrens.questionType = 'material' || childrens.questionType = 'essay']
						                    	 <b>答案:</b>
						                   		 <p>${childrens.answer}</p>
											[/#if]
					              	  </div>
		            			</div>
		            		[/#list]
		            	</div>
	            	[/#if]
	            	<div class="popboxub">
	                	<b>题干:</b
	                    <p> ${question.stemToHtml}</p>
	                    [#if question.questionType = 'single_choice' || question.questionType = 'choice' || question.questionType = 'uncertain_singlechoice' || question.questionType = 'uncertain_choice' ]
							<b>选项：</b>
	                    	 [#list question.toMetas as meta]
	                   		 	 <p>${meta}</p>
	                    	 [/#list]
	                    	 <b>答案:</b>
	                   		 <p>${question.toAnswer}</p>
						[/#if]
	                    [#if question.questionType = 'determine']
	                    	 <b>答案:</b>
	                   		 <p>${question.toAnswer}</p>
						[/#if]
	                    [#if question.questionType = 'material' || question.questionType = 'essay']
	                    	 <b>答案:</b>
	                   		 <p>${question.answer}</p>
						[/#if]
              	  </div>
	            </td>
	            <td>
	            	[#if question.questionType = 'single_choice' ]
						单选题
					[/#if]
	            	[#if question.questionType = 'choice' ]
						 多选题
					[/#if]
	            	[#if question.questionType = 'uncertain_singlechoice' ]
						不定项单选题
					[/#if]
	            	[#if question.questionType = 'uncertain_choice' ]
						不定项多选题
					[/#if]
	            	[#if question.questionType = 'determine' ]
						 判断题
					[/#if]
	            	[#if question.questionType = 'material' ]
						材料题
					[/#if]
	            	[#if question.questionType = 'essay' ]
						简答题
					[/#if]
	            	[#if question.questionType = 'blanks' ]
						填空题
					[/#if]
				</td>
	            <td class="quetitle" title="${question.stemToHtml}" id="quetitle_${question.id}">
	            	[#if question.stemToHtml?length gt 12 ]
						 ${question.stemToHtml?substring(0,12)}...
					 [#else]
						 ${question.stemToHtml}
					[/#if]
	            </td>
	            <td><a href="javascript:void(0);" onclick="addQuestion(${question.id},'${question.questionType}',${question.score},[#if question.missScore??]${question.missScore}[#else]0[/#if])" title="添加"><img src="${base}/resources/admin/images/ico_add.png" border="0" align="absmiddle"></a> </td>
            </tr>
            [/#list]
        </table>