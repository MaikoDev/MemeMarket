var scl = 24;
var BGsong;
var Score = 0;
var Border = 48;
var SnK;
var fps = 10;
var lastTime = 0;
var currentTime = 0;
var currentDir = '';
var Ftime = 0;

function preload() {
  BGsong = loadSound('assets/sounds/mu/Vanilla - Falling In Love Again.mp3');
}

function setup() {
  frameRate(fps);
  BGsong.setVolume(0.1);
  BGsong.play();
  createCanvas(960, 600);
  SnK = new Snake();
  SnK.dir(1, 0);
  currentDir = 'R'
  Foo1 = new Food();
  Foo1.place();
}

function Game_Over() {
	alert("GAME OVER!");
	location.reload();
}

function get_Time() {
  currentTime = Ftime + (frameCount%(fps + 1) / fps);

  if (frameCount%(fps + 1) === fps) {
  	Ftime += 1;
  }
}

function draw() {
  background(256);
  console.log(SnK.x);
  fill(51);
  rect(Border, Border, width - (2 * Border), height - (2 * Border));

  fill(0, 102,153);
  textSize(24);
  textAlign(CENTER, CENTER);
  text("Score: " + Score, 480, 24)

  SnK.update();
  Foo1.hitdet(SnK);
  Score = SnK.size;
  Foo1.show();
  SnK.show();
}

function keyPressed() {
  if ((keyCode === UP_ARROW) && (currentDir != 'D')) {
  	currentDir = 'U';
    SnK.dir(0, -1);
  } else if ((keyCode === RIGHT_ARROW) && (currentDir != 'L')) {
  	currentDir = 'R';
  	SnK.dir(1, 0);
  } else if ((keyCode === DOWN_ARROW) && (currentDir != 'U')) {
  	currentDir = 'D';
  	SnK.dir(0, 1);
  } else if ((keyCode === LEFT_ARROW) && (currentDir != 'R'))  {
  	currentDir = 'L';
  	SnK.dir(-1, 0);
  }
}