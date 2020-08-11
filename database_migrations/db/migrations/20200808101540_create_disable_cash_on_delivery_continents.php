<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class CreateDisableCashOnDeliveryContinents extends AbstractMigration
{

    public function change(): void
    {
        $disableCODContinents = $this->table('shop_disable_cod_continents', ['signed' => false]);
        $disableCODContinents->addColumn('shop_id', 'biginteger', ['signed' => false])
            ->addColumn('continent_code', 'string', ['limit' => 2])
            ->addForeignKey('shop_id', 'shops', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
