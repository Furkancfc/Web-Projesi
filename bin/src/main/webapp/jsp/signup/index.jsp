<body>
	<div class="signup-container">
		<h2>Sign Up</h2>
		<form action="register" method="post">
			<div class="input-group">
				<label for="username">Username</label> <input type="text"
					id="username" name="username" required>
			</div>
			<div class="input-group">
				<label for="email">Email</label> <input type="email" id="email"
					name="email" required>
			</div>
			<div class="input-group">
				<label for="password">Password</label> <input type="password"
					id="password" name="password" required>
			</div>
			<div class="input-group">
				<label for="confirm-password">Confirm Password</label> <input
					type="password" id="confirm-password" name="confirm-password"
					required>
			</div>
			<button id='submitbtn' type="submit">Sign Up</button>
		</form>
		<p class="login-link">
			Already have an account? <a href="login">Log in</a>
		</p>
	</div>
</body>
