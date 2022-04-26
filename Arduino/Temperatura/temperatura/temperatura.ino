#include <Adafruit_Sensor.h>

// Incluimos librería
#include <DHT.h>
 
// Definimos el pin digital donde se conecta el sensor
#define DHTPIN 2
// Dependiendo del tipo de sensor
#define DHTTYPE DHT11
// Definimos el pin para el potenciometro
#define pot A0
 
// Inicializamos el sensor DHT11
DHT dht(DHTPIN, DHTTYPE);
 
void setup() {
  // Inicializamos comunicación serie
  Serial.begin(9600);
 
  // Comenzamos el sensor DHT
  dht.begin();

  // Configuramos el pin analogo
  pinMode(pot,INPUT);
 
}
 
void loop() {
 
    //Codigo principal
    if(Serial.available()>0){
    char dato = Serial.read();   
        //Codigo para la temperatura  
        if(dato == 't'){      
          // Leemos la humedad relativa
          float h = dht.readHumidity();
          // Leemos la temperatura en grados centígrados (por defecto)
          float t = dht.readTemperature();
          // Leemos la temperatura en grados Fahreheit
          float f = dht.readTemperature(true);
          
          // Comprobamos si ha habido algún error en la lectura
          if (isnan(h) || isnan(t) || isnan(f)) {
            Serial.println("Error obteniendo los datos del sensor DHT11");
            return;
          }
          
          // Calcular el índice de calor en Fahreheit
          float hif = dht.computeHeatIndex(f, h);
          // Calcular el índice de calor en grados centígrados
          float hic = dht.computeHeatIndex(t, h, false);
          
          Serial.print("Humedad: ");
          Serial.print(h);
          Serial.print(" %\t");
          Serial.print("Temperatura: ");
          Serial.print(t);
          Serial.print(" *C ");
          Serial.print(f);
          Serial.print(" *F\t");
          Serial.print("Índice de calor: ");
          Serial.print(hic);
          Serial.print(" *C ");
          Serial.print(hif);
          Serial.println(" *F");
        }
        // codigo del potenciometro
        if(dato == 'p'){
          // Variable flotante 
          float valor = 0;
          valor = analogRead(pot);
          Serial.print(valor);          
        }
    }
    delay(500);
}
