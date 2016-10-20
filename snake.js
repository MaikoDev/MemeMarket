function Snake() {
	this.x = 432;
	this.y = 288;
	this.trig = false;
	this.xspeed = 0;
	this.yspeed = 0;
	this.size = 0;
	this.tailX = [];
	this.tailY = [];

	this.dir = function(x, y) {
		this.xspeed = x;
		this.yspeed = y;
	}

	this.eat = function() {
		this.size += 1;
	}

	this.update = function() {
		if ((this.x >= width-Border) || (this.y >= height-Border) || (this.x < Border) || (this.y < Border)) {
			this.xspeed = 0;
			this.yspeed = 0;
			this.size = 0;
			if (!this.trig) {
				BGsong.stop();
				Game_Over();
				this.trig = !this.trig;
			}
		}

		if (this.size > 0) {
			this.tailX.splice(this.size - 1, 1);
			this.tailY.splice(this.size - 1, 1);
			this.tailX.unshift(this.x);
			this.tailY.unshift(this.y);
		}

		console.log(this.size);

		this.x = this.x + this.xspeed*scl;
		this.y = this.y + this.yspeed*scl;

		for (i = 0; i < this.size; i++) {
			if ((this.x === this.tailX[i]) && (this.y === this.tailY[i])) {
				Game_Over();
			}
		}

		this.x = constrain(this.x, 0, width-scl);
		this.y = constrain(this.y, 0, height-scl);
	}

	this.show = function() {
		fill(256);
		rect(this.x, this.y, scl, scl);

		for (i = 0; i < this.size; i++) {
			rect(this.tailX[i], this.tailY[i], scl, scl);
		}
	}
}