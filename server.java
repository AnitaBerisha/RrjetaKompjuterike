

            // Kontrolli i privilegjeve (Supozojmë se klienti i parë me emrin 'Admin' ka qasje të plotë)
            String response;
            if (clientMessage.contains("Admin")) {
                response = "STATUS: Qasje e plotë (RWX). Mund të modifikoni folderat në server.";
                System.out.println("Klientit i u dha qasje e plotë.");
            } else {
                response = "STATUS: Qasje vetëm për lexim (Read-only).";
            }

            // Dërgimi i përgjigjes
            out.println("Përshëndetje! " + response + " Ju faleminderit që jeni pjesë e FIEK.");

        } catch (IOException e) {
            System.out.println("Gabim me klientin: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
