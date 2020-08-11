<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableDisableCodCountries extends AbstractMigration
{

    public function change(): void
    {
        $disableCODContinents = $this->table('shop_disable_cod_countries', ['signed' => false]);
        $disableCODContinents->addColumn('shop_id', 'biginteger', ['signed' => false])
            ->addColumn('country_code', 'string', ['limit' => 2])
            ->addForeignKey('shop_id', 'shops', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
