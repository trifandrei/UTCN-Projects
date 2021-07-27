void StartMotor (int m1, int m2, int speed)
{
  digitalWrite(m2, 0);
  analogWrite(m1,speed);
}
void StartMotor2 (int m1, int m2, int speed)
{
  digitalWrite(m1, 0);
  analogWrite(m2,speed);
}
