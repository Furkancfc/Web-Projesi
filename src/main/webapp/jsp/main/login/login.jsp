<body>
	<div class="login-container">
		<h2>Login</h2>
		<form action="/project2/login" method="post">
			<div class="input-group">
				<label for="email">Email</label> <input type="text" id="email"
					name="email" required>
			</div>
			<div class="input-group">
				<label for="password">Password</label> <input type="password"
					id="password" name="password" required>
			</div>
			<button id='submitbtn' type="submit">Login</button>
		</form>
		<p class="signup-link">
			Don't have an account? <a href="register">Sign up</a>
		</p>
	</div>
</body>

