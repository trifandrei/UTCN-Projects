double getDistance()
{
 digitalWrite(trigPin, LOW); 
 delayMicroseconds(2); 
 digitalWrite(trigPin, HIGH);
 delayMicroseconds(10); 
 digitalWrite(trigPin, LOW);
 distance_cm = pulseIn(echoPin,HIGH)/29/2;
 delay(100);
 Serial.print(distance_cm);
 Serial.println();
 return distance_cm;
}
