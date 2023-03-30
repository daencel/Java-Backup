<h2>Benutzerverwaltung</h2>
<form method="post" action="index.php?id=<?= $_GET["id"][0] ?>0" enctype="multipart/form-data">

    <table>

        <tr>
            <td>
                <label>Benutzername:</label>
            </td>
            <td>
                <label>
                    <input type="text" name="username" value="<?= $user->getUsername() ?>">
                </label>
            </td>
            <td class="error">
                <?= $user->getError("username") ?>
            </td>
        </tr>

        <tr>
            <td>
                <label>Passwort:</label>
            </td>
            <td>
                <label>
                    <input type="password" name="password" value="<?= $user->getPassword() ?>">
                </label>
            </td>
            <td class="error">
                <?= $user->getError("password") ?>
            </td>
        </tr>

        <tr>
            <td>
                <label>Passwort wiederholen:</label>
            </td>
            <td>
                <input type="password" name="passwordRepeat" value="<?= $user->getPasswordRepeat() ?>">
            </td>
            <td class="error">
                <?= $user->getError("passwordRepeat") ?>
            </td>
        </tr>

        <tr>
            <td>
                <label>Geschlecht:</label>
            </td>
            <td>
                <input type="radio" name="gender" id="female"
                       value="Weiblich" <?= $user->getMale() == true ? '' : 'checked' ?>>
                <label for="female">Weiblich</label>
                <input type="radio" name="gender" id="male"
                       value="Männlich" <?= $user->getMale() == true ? 'checked' : '' ?>>
                <label for="male">Männlich</label>
            </td>
            <td class="error">
                <?= $user->getError("gender") ?>
            </td>
        </tr>

        <tr>
            <td>
                <label>Geburtsdatum:</label>
            </td>
            <td>
                <label>
                    <input type="date" name="birthday" value="<?= $user->getBirthDate() ?>">
                </label>
            </td>
            <td class="error">
                <?= $user->getError("birthday") ?>
            </td>
        </tr>

        <tr>
            <td>
                <label>Bewertung:</label>
            </td>
            <td>
                <label>
                    <input type="number" name="rating" min="1" max="5" step="any" value="<?= $user->getRating() ?>">
                </label>
            </td>
            <td class="error">
                <?= $user->getError("rating") ?>
            </td>
        </tr>

        <tr>
            <td>
                <label>Profilbild:</label>
            </td>
            <td>
                <?php if ($_GET["id"][0] == "3" && $user->getImage() != null) {
                    if ($user->getError("username") != null || $user->getError("password") != null || $user->getError("passwordRepeat") != null || $user->getError("rating") != null || $user->getError("birthday") != null) {
                        $user->setError("image", "Bild kann nicht angezeigt werden, wenn Fehler vorhanden sind.");
                    } else {
                        $user->unsetError("image");
                        ?>
                        <img src="scripts/scr.showImage.php?username=<?= $_SESSION["oldUsername"] ?>"
                             style=" width: 200px; height: 200px" alt="Profilbild">
                        <br>
                    <?php }
                } ?>
                <input type="file" name="image" accept="image/png, image/jpeg">
                <?php if ($_GET["id"][0] == "3" && $user->getImage() != null) { ?>
                    <form method="post" action="index.php?id=<?= $_GET["id"][0] ?>0" enctype="multipart/form-data">
                        <input type="submit" name="deleteImage" value="Bild löschen">
                    </form>
                <?php
                } else
                    $user->unsetError("image");
                ?>
            </td>
            <td class="error">
                <?= $user->getError("image") ?>
            </td>
        </tr>

        <tr>
            <td></td>
            <td>
                <?php
                if ($_GET["id"] == "2" || $_GET["id"] == "20") {
                    echo "<input type=\"submit\" name=\"submit\" value=\"Hinzufügen\">";
                } elseif ($_GET["id"] == "3" || $_GET["id"] == "30") {
                    echo "<input type=\"submit\" name=\"submit\" value=\"Ändern\">";
                }
                ?>
                <input type="reset" name="reset" value="Zurücksetzen">

            </td>
            <td></td>
        </tr>
    </table>
</form>
