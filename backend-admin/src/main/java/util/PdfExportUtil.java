package util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import model.Commande;
import model.LigneCommande;

import java.io.OutputStream;
import java.util.List;

public class PdfExportUtil {

    public static void exportCommandes(List<Commande> commandes, OutputStream out) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, out);
        document.open();

        // Titre principal
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
        Paragraph title = new Paragraph("Liste des Commandes", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(15f);
        document.add(title);

        for (Commande cmd : commandes) {
            // Sous-titre
            Font subTitleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.DARK_GRAY);
            Paragraph subTitle = new Paragraph("Commande #" + cmd.getId(), subTitleFont);
            subTitle.setSpacingBefore(10);
            subTitle.setSpacingAfter(5);
            document.add(subTitle);

            // Informations client
            PdfPTable infoTable = new PdfPTable(2);
            infoTable.setWidthPercentage(100);
            infoTable.setSpacingBefore(5);
            infoTable.setWidths(new float[]{2, 5});

            infoTable.addCell(getBoldCell("Client"));
            infoTable.addCell(getNormalCell(cmd.getUtilisateur() != null ? cmd.getUtilisateur().getNom() : "Inconnu"));

            infoTable.addCell(getBoldCell("Date"));
            infoTable.addCell(getNormalCell(cmd.getDate() != null ? cmd.getDate().toString() : "Non précisée"));

            infoTable.addCell(getBoldCell("Montant total"));
            infoTable.addCell(getNormalCell(cmd.getMontant() != null ? String.format("%.2f €", cmd.getMontant()) : "0.00 €"));

            infoTable.addCell(getBoldCell("Statut"));
            infoTable.addCell(getNormalCell(cmd.getStatus() != null ? cmd.getStatus().toString() : "Indéfini"));

            document.add(infoTable);

            // Lignes de commande
            if (cmd.getLigneCommandes() != null && !cmd.getLigneCommandes().isEmpty()) {
                Paragraph lignesTitle = new Paragraph("Produits commandés :", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
                lignesTitle.setSpacingBefore(10);
                lignesTitle.setSpacingAfter(5);
                document.add(lignesTitle);

                PdfPTable lignesTable = new PdfPTable(4);
                lignesTable.setWidthPercentage(100);
                lignesTable.setWidths(new float[]{4, 2, 2, 2});

                lignesTable.addCell(getHeaderCell("Article"));
                lignesTable.addCell(getHeaderCell("Prix Unitaire"));
                lignesTable.addCell(getHeaderCell("Quantité"));
                lignesTable.addCell(getHeaderCell("Total"));

                for (LigneCommande ligne : cmd.getLigneCommandes()) {
                    lignesTable.addCell(getNormalCell(ligne.getArticle() != null ? ligne.getArticle().getNom() : "Article inconnu"));
                    lignesTable.addCell(getNormalCell(String.format("%.2f €", ligne.getPrix())));
                    lignesTable.addCell(getNormalCell(String.valueOf(ligne.getQuantite())));
                    lignesTable.addCell(getNormalCell(String.format("%.2f €", ligne.getPrix() * ligne.getQuantite())));
                }

                document.add(lignesTable);
            }

            document.add(new Paragraph(" "));
            document.add(new LineSeparator());
        }

        document.close();
    }

    private static PdfPCell getBoldCell(String content) {
        Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11);
        PdfPCell cell = new PdfPCell(new Phrase(content, boldFont));
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private static PdfPCell getNormalCell(String content) {
        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 11);
        PdfPCell cell = new PdfPCell(new Phrase(content, normalFont));
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private static PdfPCell getHeaderCell(String content) {
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, BaseColor.WHITE);
        PdfPCell cell = new PdfPCell(new Phrase(content, headerFont));
        cell.setBackgroundColor(BaseColor.GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }
}
