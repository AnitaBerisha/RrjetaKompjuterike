 // Mesazhi për qasje në folderat
            if (pergjigja.contains("RWX")) {
                System.out.println("Sistemi: Ju keni qasje WRITE/READ/EXECUTE në server.");
            } else {
                System.out.println("Sistemi: Ju keni vetëm qasje READ.");
            }

        } catch (IOException e) {
            System.err.println("Gabim: Nuk u arrit lidhja me serverin në " + serverIP + ":" + serverPort);
        }
    }
}
