import java.util.Scanner;

public class GestionStock {
    static final int TAILLE_MAX = 100;

    // Tableaux parallèles
    static int[] codesProduits = new int[TAILLE_MAX];
    static String[] nomsProduits = new String[TAILLE_MAX];
    static int[] quantites = new int[TAILLE_MAX];
    static double[] prix = new double[TAILLE_MAX];

    // Nombre actuel de produits
    static int nbProduits = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            printMenu();
            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le retour à la ligne
            switch (choix) {
                case 1:
                    ajouterProduit(scanner);
                    break;
                case 2:
                    modifierProduit(scanner);
                    break;
                case 3:
                    supprimerProduit(scanner);
                    break;
                case 4:
                    afficherProduits();
                    break;
                case 5:
                    rechercherProduit(scanner);
                    break;
                case 6:
                    calculerValeurStock();
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        } while (choix != 0);

        scanner.close();
    }

    public static void printMenu() {
        System.out.println("\n--- Menu Gestion de Stock ---");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Modifier un produit");
        System.out.println("3. Supprimer un produit");
        System.out.println("4. Afficher les produits");
        System.out.println("5. Rechercher un produit");
        System.out.println("6. Calculer la valeur totale du stock");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    public static void ajouterProduit(Scanner scanner) {
        if (nbProduits >= TAILLE_MAX) {
            System.out.println("Erreur : le stock est plein.");
            return;
        }

        System.out.print("Code produit : ");
        int code = scanner.nextInt();
        scanner.nextLine(); // Consommer le retour à la ligne
        System.out.print("Nom produit : ");
        String nom = scanner.nextLine();
        System.out.print("Quantité : ");
        int quantite = scanner.nextInt();
        System.out.print("Prix unitaire : ");
        double prixUnitaire = scanner.nextDouble();

        codesProduits[nbProduits] = code;
        nomsProduits[nbProduits] = nom;
        quantites[nbProduits] = quantite;
        prix[nbProduits] = prixUnitaire;

        nbProduits++;
        System.out.println("Produit ajouté avec succès.");
    }

    public static void modifierProduit(Scanner scanner) {
        System.out.print("Code produit à modifier : ");
        int code = scanner.nextInt();
        scanner.nextLine();

        int index = trouverIndexParCode(code);
        if (index == -1) {
            System.out.println("Produit introuvable.");
            return;
        }

        System.out.print("Nouveau nom : ");
        String nouveauNom = scanner.nextLine();
        System.out.print("Nouvelle quantité : ");
        int nouvelleQuantite = scanner.nextInt();
        System.out.print("Nouveau prix unitaire : ");
        double nouveauPrix = scanner.nextDouble();

        nomsProduits[index] = nouveauNom;
        quantites[index] = nouvelleQuantite;
        prix[index] = nouveauPrix;

        System.out.println("Produit modifié avec succès.");
    }

    public static void supprimerProduit(Scanner scanner) {
        System.out.print("Code produit à supprimer : ");
        int code = scanner.nextInt();

        int index = trouverIndexParCode(code);
        if (index == -1) {
            System.out.println("Produit introuvable.");
            return;
        }

        for (int i = index; i < nbProduits - 1; i++) {
            codesProduits[i] = codesProduits[i + 1];
            nomsProduits[i] = nomsProduits[i + 1];
            quantites[i] = quantites[i + 1];
            prix[i] = prix[i + 1];
        }

        nbProduits--;
        System.out.println("Produit supprimé avec succès.");
    }

    public static void afficherProduits() {
        if (nbProduits == 0) {
            System.out.println("Aucun produit en stock.");
            return;
        }

        System.out.println("\n--- Liste des produits ---");
        for (int i = 0; i < nbProduits; i++) {
            System.out.printf("Code: %d | Nom: %s | Quantité: %d | Prix: %.2f\n",
                    codesProduits[i], nomsProduits[i], quantites[i], prix[i]);
        }
    }

    public static void rechercherProduit(Scanner scanner) {
        System.out.print("Nom du produit à rechercher : ");
        String nomRecherche = scanner.nextLine();

        boolean trouve = false;
        for (int i = 0; i < nbProduits; i++) {
            if (nomsProduits[i].equalsIgnoreCase(nomRecherche)) {
                System.out.printf("Code: %d | Nom: %s | Quantité: %d | Prix: %.2f\n",
                        codesProduits[i], nomsProduits[i], quantites[i], prix[i]);
                trouve = true;
            }
        }

        if (!trouve) {
            System.out.println("Produit introuvable.");
        }
    }

    public static void calculerValeurStock() {
        double valeurTotale = 0;
        for (int i = 0; i < nbProduits; i++) {
            valeurTotale += quantites[i] * prix[i];
        }

        System.out.printf("Valeur totale du stock : %.2f\n", valeurTotale);
    }

    private static int trouverIndexParCode(int code) {
        for (int i = 0; i < nbProduits; i++) {
            if (codesProduits[i] == code) {
                return i;
            }
        }
        return -1;
    }
}
