#include "DHT.h"
#include <SoftwareSerial.h>

#define DHTPIN 2
#define DHTTYPE DHT11

#define Temperatura 0
#define Humedad 1
#define Aparente 2

DHT dht(DHTPIN, DHTTYPE);
SoftwareSerial Blue(2,3);

char data;

void setup() {
  dht.begin();  
  Serial.begin(9600);
  Blue.begin(9600);
  
}

void loop() {
  while(Blue.available() == 0);
  if(Blue.available()>0){
    data = Blue.peek();
  }
  delay(400);
  switch (data){
      case 't':
      case 'T':
        Serial.print("Temperatura:");
        Serial.print(CalculoAmbiente(Temperatura));
        break;
      case 'h':
      case 'H':
        Serial.print("Humedad:");
        Serial.print(CalculoAmbiente(Humedad));
        break;
      case 'a':
      case 'A':
        Serial.print("Aparente:");
        Serial.print(CalculoAmbiente(Aparente));
        break;      
    }
}

float CalculoAmbiente (int Opcion){
  float T = dht.readTemperature();
  float H = dht.readHumidity();
  float A = dht.computeHeatIndex(T, H, false);
  switch (Opcion){
    case Temperatura:
      return T;
      break;
    case Humedad:
      return H;
      break;
     case Aparente:      
      return A;
      break;
     default:
      return -100;
      break;
  }
}
