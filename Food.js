function Food() {
	this.x = 0;
	this.y = 0;

	this.place = function() {
		this.x = (floor(random(Border/scl, (width - Border)/scl))) * scl;
		this.y = (floor(random(Border/scl, (height - Border)/scl))) * scl;
	}

	this.hitdet = function(obj) {
		if ((obj.x === this.x) && (obj.y === this.y)) {
			this.place();
			obj.eat();
		}
	}

	this.update = function() {

	}

	this.show= function() {
		fill('red');
		rect(this.x, this.y, scl, scl);
	}
}