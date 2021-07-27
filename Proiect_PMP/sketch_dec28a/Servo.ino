Servo srv;
void moveServo(int angle)
{
 srv.attach(8);
 srv.write(angle);
 delay(1000);
 srv.detach();
}
