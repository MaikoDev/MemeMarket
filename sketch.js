var yPos = 0;
var xLoc = 0;
var lastTime = 0;
var currentTime = 0;
var Ftime = 0;
function setup() {  // setup() runs once
  frameRate(60);
  createCanvas(960, 600)
}
function draw() {
  currentTime = Ftime + (frameCount%61 / 60);

  if (frameCount%61 == 60) {
  	Ftime += 1;
  }

  background(204);
  yPos = yPos - 0.5;
  if (yPos < 0) {
    yPos = height;
  }

  if ((currentTime - lastTime) >= 0.5) {
  	lastTime = currentTime;
  	console.log("EXCUTE");
  	xLoc = xLoc + 24;
  }

  fill(51)
  rect(xLoc, 0, 24, 24);
  line(0, yPos, width, yPos);
}