// Incluimos librería
#include <DHT.h>
#include <SoftwareSerial.h>  // libreria que permite establecer pines digitales
        // para comunicacion serie

#define DHTTYPE DHT11   // DHT 22  (AM2302), AM2321

#define analogPin      1          // analog pin for measuring capacitor voltage
#define chargePin      13         // pin to charge the capacitor - connected to one end of the charging resistor
#define dischargePin   11         // pin to discharge the capacitor
#define resistorValue  10000.0F   // change this to whatever resistor value you are using

#define analogVolt 0
#define tacometroPin 9

SoftwareSerial miBT(2, 3);  // pin 2 como RX, pin 3 como TX
                                  
int count = 0;
float Tiempo_giro = 0.0;
float RPM;
                                  
// Temperatura
const int DHTPin = 5;     // what digital pin we're connected to
float h;
float t ;
DHT dht(DHTPin, DHTTYPE);

// Capcitancia 
unsigned long startTime;
unsigned long elapsedTime;
float microFarads;                // floating point variable to preserve precision, make calculations
float nanoFarads;

float lectura;
float volt;

String msg;

void setup(){
  pinMode(tacometroPin , INPUT);
  pinMode(analogVolt,INPUT);
  pinMode(chargePin, OUTPUT);     // set chargePin to output
  digitalWrite(chargePin, LOW);
  miBT.begin(9600);    // comunicacion serie entre Arduino y el modulo a 38400 bps
  Serial.begin(9600);             // initialize serial transmission for debugging
  Serial.println("listo");
  dht.begin();
}

void loop(){
  // To read message received from other Bluetooth Device
  if (miBT.available() > 0){ // Check if there is data coming
    msg = miBT.readString(); // Read the message as String
    Serial.println("Android Command: " + msg);
  }

  //Capacitancia();
  //Voltaje();
  Temp();
  //Tacometro();
  enviarlecturas();
  delay(50);
}

void enviarlecturas(){
  String temp;
  if (microFarads > 1){
    temp = String((long)microFarads) + " microfarads";
  }
  else{
    // if value is smaller than one microFarad, convert to nanoFarads (10^-9 Farad).
    // This is  a workaround because miBT.print will not print floats
    nanoFarads = microFarads * 1000.0;      // multiply by 1000 to convert to nanoFarads (10^-9 Farads)
    temp = String((long)microFarads) + " nanoFarads";
  }
   miBT.println(String(h)+ "&" + String(t) + "&" + String(volt) + "&" + String(temp) + "&" + String(RPM));
}

void CalcularRPM(){
  Tiempo_giro = millis()- Tiempo_giro;
  RPM = 60000.0 / Tiempo_giro;
  Tiempo_giro = millis();
}

void Tacometro(){
  if(digitalRead(tacometroPin)!= HIGH){
    while (digitalRead(tacometroPin) != HIGH){}
      CalcularRPM();
  }
  
  Serial.println(RPM);
}

void Temp(){
    // Reading temperature or humidity takes about 250 milliseconds!
    h = dht.readHumidity();
    t = dht.readTemperature();

   if (isnan(h) || isnan(t)) {
      Serial.println("Failed to read from DHT sensor!");
      return;
   }
}

void Voltaje(){
  lectura = analogRead(analogVolt);
  volt = lectura /1023 * 5.0;
}

void Capacitancia(){
  digitalWrite(chargePin, HIGH);  // set chargePin HIGH and capacitor charging
  startTime = millis();
  while(analogRead(analogPin) < 648){       // 647 is 63.2% of 1023, which corresponds to full-scale voltage
  }
  elapsedTime= millis() - startTime;
 
 // convert milliseconds to seconds ( 10^-3 ) and Farads to microFarads ( 10^6 ),  net 10^3 (1000)
  microFarads = ((float)elapsedTime / resistorValue) * 1000;
  //Serial.print(elapsedTime);       // print the value to serial port
  //Serial.print(" mS    ");         // print units and carriage return

  /* dicharge the capacitor  */
  digitalWrite(chargePin, LOW);             // set charge pin to  LOW
  pinMode(dischargePin, OUTPUT);            // set discharge pin to output
  digitalWrite(dischargePin, LOW);          // set discharge pin LOW
  while(analogRead(analogPin) > 0){         // wait until capacitor is completely discharged
  }
  pinMode(dischargePin, INPUT);            // set discharge pin back to input
}
