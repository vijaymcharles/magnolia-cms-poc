		<main class="main" id="main" role="main">	
			<h1 style="text-align: center">PREVIEW OF HEADER, FOOTER, SUBFOOTER AND GLOBAL PROMOTION MESSAGE</h1>
		</main>

		<div class="framework-component">
			<div data-component="SubFooter">
			</div>
			<script type="application/json">
					${model.getJson("http://csione-test.us-east-1.elasticbeanstalk.com/navbobulator/subfooter.json")}
			</script>
		</div>

		<div class="framework-component">
			<div data-component="Footer">
			</div>
			<script type="application/json">
					${model.getJson("http://csione-test.us-east-1.elasticbeanstalk.com/navbobulator/footer.json")}
			</script>
		</div>
