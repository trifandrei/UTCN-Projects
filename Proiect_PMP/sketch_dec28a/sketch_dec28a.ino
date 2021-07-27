#include <Servo.h>

#define mpin00 5
#define mpin01 6

#define mpin10 4
#define mpin11 11

#define echoPin 3
#define trigPin 2

#define S1 22
#define S0 23
#define S3 31
#define S2 30
#define sensorOut 26


double distance_cm=0;
int redFrequency = 0;
int greenFrequency = 0;
int blueFrequency = 0;

void setup() {
  // put your setup code here, to run once:
 Serial.begin(9600);
 
 moveServo(60);
 
 pinMode(trigPin, OUTPUT);
 pinMode(echoPin, INPUT);
 
 digitalWrite(mpin00, 0);
 digitalWrite(mpin01, 0);
 digitalWrite(mpin10, 0);
 digitalWrite(mpin11, 0);
 pinMode (mpin00, OUTPUT);
 pinMode (mpin01, OUTPUT);
 pinMode (mpin10, OUTPUT);
 pinMode (mpin11, OUTPUT);
 
  pinMode(S0, OUTPUT);
  pinMode(S1, OUTPUT);
  pinMode(S2, OUTPUT);
  pinMode(S3, OUTPUT);
  pinMode(sensorOut, INPUT);  
  digitalWrite(S0,HIGH);
  digitalWrite(S1,LOW);  
}

void loop() {
  // put your main code here, to run repeatedly:
  StartMotor (mpin00, mpin01, 65);
  StartMotor (mpin10, mpin11, 65);
  getColor();

  if(colorId()==1)
    moveServo(85);
  if(colorId()==2)
    moveServo(160);
    
 if (getDistance()<7)
    {
      moveServo(60);
      StartMotor2 (mpin00, mpin01, 0);
      StartMotor2 (mpin10, mpin11, 0);
      delay(1000);
      StartMotor2 (mpin00, mpin01, 80);
      StartMotor2 (mpin10, mpin11, 80);
      delay(5000);
     }
    
}
