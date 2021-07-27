 void getColor()
 {
  //setare culoare rosu.
  digitalWrite(S2,LOW);
  digitalWrite(S3,LOW); 
  
  //citirea frecventei de output
  redFrequency = pulseIn(sensorOut, LOW);
  
   // afisare valori rosii
  Serial.print("R = ");
  Serial.print(redFrequency);  
  delay(100);
  
  // Setarea culori verzi
  digitalWrite(S2,HIGH);
  digitalWrite(S3,HIGH);
  
  // citirea frecventei verzi
  greenFrequency = pulseIn(sensorOut, LOW);
  
  // afisare verde  
  Serial.print(" G = ");
  Serial.print(greenFrequency);  
  delay(100);
 
  //setare albastru
  digitalWrite(S2,LOW);
  digitalWrite(S3,HIGH);
  
  // citirea frecventei albastre
  blueFrequency = pulseIn(sensorOut, LOW);
  
  //afisare albastru
  Serial.print(" B = ");
  Serial.println(blueFrequency);  
  delay(100);
 }

 int colorId()
 {
   if ((greenFrequency>60)&&(blueFrequency>60) && (redFrequency<60))
   {
    return 1; //red
   }
    if ((greenFrequency>60) && (blueFrequency<65) && (redFrequency>60))
   {
    return 2;//blue
   }
   return 0;
 }
