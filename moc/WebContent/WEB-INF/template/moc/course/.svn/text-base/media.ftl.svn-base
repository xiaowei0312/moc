<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<title>谋刻职业教育在线测评与学习平台</title>
<link href="${base}/resources/moc/mediaelement/build/mediaelementplayer.min.css" rel="stylesheet" type="text/css" />
<script src="${base}/resources/moc/js/jquery-1.9.1.js" type="text/javascript" ></script>
<script src="${base}/resources/moc/js/common.js" type="text/javascript" ></script>
<script src="${base}/resources/moc/mediaelement/build/mediaelement-and-player.min.js" type="text/javascript" ></script>
<script src="${base}/resources/moc/swfobject/swfobject.js" type="text/javascript" ></script>
<style type="text/css">
html, body {
    height: 100%;
    background-color: transparent;
}
body{
	margin:0;
	padding:0;
}
</style>
<script type="text/javascript">


$().ready(function() {
	

	
	$('audio').mediaelementplayer({
		success: function(media, node, player) {
			/*var events = ['loadstart', 'play','pause', 'ended'];
			for (var i=0, il=events.length; i<il; i++) {
				var eventName = events[i];
				media.addEventListener(events[i], function(e) {
					if('play' == e.type){//音频页面加载的时候就触发loadstart,所以用play状态
					
					}
				});
			}*/
		}
	});
	

});
</script>
</head>
[#if uploadFiles??]
	<body style="height:100%;">
		[#if uploadFiles.fileType == "audio"]
			<div style="margin:0 auto; width:400px; margin-top:10%">
				<audio  src="${setting.cloudServerSite}/file/view/${token}/${uploadFiles.id}.jhtml" type="audio/mp3" controls="controls" preload="none"></audio>
			</div>	
		[#elseif uploadFiles.fileType == "video"]
			<script type="text/javascript">
			var flashvars = {
			    src: "${setting.cloudServerSite}/file/view/${token}/${uploadFiles.id}.flv",
			    autoPlay:true,
			    autoRewind:false,
			    loop:false,
			    bufferTime:8,
			    javascriptCallbackFunction: "onJSBridge"
			};
			
			
			var params = {
			    allowFullScreen: true
			    , allowScriptAccess: "always"
			    , bgcolor: "#000000"
			};
			var attrs = {
			    name: "player"
			};
			
			swfobject.embedSWF("http://cdn.staticfile.org/GrindPlayerCN/1.0.2/GrindPlayer.swf", "player", "100%", "100%", "10.2", null, flashvars, params, attrs);
			var isPlayed = false;
			var player;
			function onJSBridge(playerId, event, data) {
			    switch(event) {
			        case "onJavaScriptBridgeCreated":
			            player = document.getElementById(playerId);
			            break;
			        case "ready":
			        case "loading":
			        case "playing":
			        case "paused":
			        case "buffering":
			
			        // other events
			        case "mediaSize":
			        case "seeking":
			        case "seeked":
			        case "volumeChange":
			        case "durationChange":
			        case "timeChange":
			        case "progress": // for progressive download only
			        case "complete":
			        case "advertisement":
			
			        default:
			            // console.log(event, data);
			            break;
			    }
			}
			</script>
		
			<div style="margin:0 auto;width:100%;height:100%;" id="player">
				
	 		</div>
	    [/#if]
	 </body>
[#elseif lesson.type=="text"]
	<body style="background-color:#fff">
		<div>
			${lesson.content}
		</div>
	</body>
[/#if]	
</html>
