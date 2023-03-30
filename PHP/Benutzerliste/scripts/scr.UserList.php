<script type="text/javascript">
    function deleteUser() {
        return confirm("Soll der Benutzer wirlich gelöscht werden?");
    }
</script>
<?php $list = $userList->getUsers(); ?>
<h2>Benutzerliste</h2>
<?php if (!$list) { ?>
    <p>Keine Benutzer vorhanden</p>
<?php } else { ?>
    <table>
        <tr>
            <th>Benutzername</th>
            <th>Geschlecht</th>
            <th>Geburtsdatum</th>
            <th>Bewertung</th>
            <th></th>
        </tr>
        <?php forEach ($list as $key => $value) { ?>
            <tr>
                <td><?= $value->getUsername() ?></td>
                <td><?= $value->getMale() ? "Männlich" : "Weiblich" ?></td>
                <td><?= date('d.m.Y', strtotime($value->getbirthdate())); ?></td>
                <td><?= number_format((float)$value->getRating(), 2, ',', ''); ?></td>
                <td>
                    <a href="index.php?id=3&username=<?= $value->getUsername() ?>">Bearbeiten</a>&nbsp
                    <a href="index.php?id=4&username=<?= $value->getUsername() ?>"
                       onclick="return deleteUser()">Löschen</a>
                </td>
            </tr>
        <?php } ?>
    </table>
<?php } ?>